package projeto_tomorrow.demo.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto_tomorrow.demo.DTO.ConsultaDTO.ConsultaDTORequest;
import projeto_tomorrow.demo.DTO.ConsultaDTO.ConsultaDTOResponse;
import projeto_tomorrow.demo.DTO.ConsultaDTO.ReconsultaDTORequest;
import projeto_tomorrow.demo.Entity.Consulta;
import projeto_tomorrow.demo.Entity.PerfilMedico;
import projeto_tomorrow.demo.Entity.PerfilPaciente;
import projeto_tomorrow.demo.Entity.enums.StatusConsulta;
import projeto_tomorrow.demo.Exceptions.NotFoundException;
import projeto_tomorrow.demo.Repository.ConsultaRepository;
import projeto_tomorrow.demo.Repository.MedicoRepository;
import projeto_tomorrow.demo.Repository.PacienteRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public ConsultaDTOResponse criarConsulta (ConsultaDTORequest dto){
        PerfilMedico medico = medicoRepository.findById(dto.medicoId())
                .orElseThrow(() -> new NotFoundException("Medico não encontrado"));
        
        PerfilPaciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado"));
        
        Consulta consulta = new Consulta();
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDataHora(dto.dataHora());
        consulta.setObservacao(dto.observacao());
        consulta.setStatusConsulta(StatusConsulta.AGENDADA);
        
        consultaRepository.save(consulta);
        
        return new ConsultaDTOResponse(
                medico.getUsuario().getNome(),
                paciente.getUsuario().getNome(),
                consulta.getDataHora(),
                consulta.getObservacao(),
                consulta.getStatusConsulta()
        );
        
    }

    public List<ConsultaDTOResponse> listAll() {
        List<Consulta> consultas = consultaRepository.findAll();
        return consultas.stream()
                .map(consulta -> new ConsultaDTOResponse(
                        consulta.getMedico().getUsuario().getNome(),
                        consulta.getPaciente().getUsuario().getNome(),
                        consulta.getDataHora(),
                        consulta.getObservacao(),
                        consulta.getStatusConsulta()
                )).collect(Collectors.toList());
    }
    
    public ConsultaDTOResponse findById(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada"));
        return new ConsultaDTOResponse(
                consulta.getMedico().getUsuario().getNome(),
                consulta.getPaciente().getUsuario().getNome(),
                consulta.getDataHora(),
                consulta.getObservacao(),
                consulta.getStatusConsulta()
        );
    }
    
    public ConsultaDTOResponse update(Long id, ConsultaDTORequest dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada"));
        
        if (dto.medicoId() != null) {
            PerfilMedico medico = medicoRepository.findById(dto.medicoId())
                    .orElseThrow(() -> new NotFoundException("Medico não encontrado"));
            consulta.setMedico(medico);
        }
        
        if (dto.pacienteId() != null) {
            PerfilPaciente paciente = pacienteRepository.findById(dto.pacienteId())
                    .orElseThrow(() -> new NotFoundException("Paciente não encontrado"));
            consulta.setPaciente(paciente);
        }
        
        if (dto.observacao() != null && !dto.observacao().isEmpty()) {
            consulta.setObservacao(dto.observacao());
        }
        
        consultaRepository.save(consulta);
        
        return new ConsultaDTOResponse(
                consulta.getMedico().getUsuario().getNome(),
                consulta.getPaciente().getUsuario().getNome(),
                consulta.getDataHora(),
                consulta.getObservacao(),
                consulta.getStatusConsulta()
        );
    }
    
    public List<ConsultaDTOResponse> findByStatus(StatusConsulta status) {
        List<Consulta> consultas = consultaRepository.findByStatusConsulta(status);
        return consultas.stream()
                .map(consulta -> new ConsultaDTOResponse(
                        consulta.getMedico().getUsuario().getNome(),
                        consulta.getPaciente().getUsuario().getNome(),
                        consulta.getDataHora(),
                        consulta.getObservacao(),
                        consulta.getStatusConsulta()
                )).collect(Collectors.toList());
    }
    
    public void delete(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada"));
        consultaRepository.delete(consulta);
    }

    public ConsultaDTOResponse consultaRealizada(Long id) {
        return atualizarStatus(id, StatusConsulta.REALIZADA);
    }
    
    public ConsultaDTOResponse concluirConsulta(Long id) {
        return atualizarStatus(id, StatusConsulta.CONCLUIDA);
    }
    
    public ConsultaDTOResponse cancelarConsulta(Long id) {
        return atualizarStatus(id, StatusConsulta.CANCELADA);
    }
    
    public ConsultaDTOResponse reconsulta(Long id, ReconsultaDTORequest dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada"));

        if (!consulta.getStatusConsulta().equals(StatusConsulta.REALIZADA)) {
            throw new IllegalArgumentException("A reconsulta só pode ser feita a partir de uma consulta realizada.");
        }

        if (dto.nomeMedico() != null){
            consulta.setMedico(dto.nomeMedico());
        }

        consulta.setDataHora(dto.dataHora());
        consulta.setObservacao(dto.observacao());
        consulta.setStatusConsulta(StatusConsulta.RECONSULTA);

        consultaRepository.save(consulta);

        return new ConsultaDTOResponse(
                consulta.getMedico().getUsuario().getNome(),
                consulta.getPaciente().getUsuario().getNome(),
                consulta.getDataHora(),
                consulta.getObservacao(),
                consulta.getStatusConsulta()
        );

    }
    
    private ConsultaDTOResponse atualizarStatus(Long id, StatusConsulta novoStatus) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada"));
        
        consulta.setStatusConsulta(novoStatus);
        consultaRepository.save(consulta);
        
        return new ConsultaDTOResponse(
                consulta.getMedico().getUsuario().getNome(),
                consulta.getPaciente().getUsuario().getNome(),
                consulta.getDataHora(),
                consulta.getObservacao(),
                consulta.getStatusConsulta()
        );
    }


}