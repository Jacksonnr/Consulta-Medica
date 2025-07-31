package projeto_tomorrow.demo.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto_tomorrow.demo.DTO.ConsultaDTO.ConsultaDTORequest;
import projeto_tomorrow.demo.Entity.Consulta;
import projeto_tomorrow.demo.Entity.PerfilMedico;
import projeto_tomorrow.demo.Entity.PerfilPaciente;
import projeto_tomorrow.demo.Entity.enums.StatusConsulta;
import projeto_tomorrow.demo.Exceptions.NotFoundException;
import projeto_tomorrow.demo.Repository.ConsultaRepository;
import projeto_tomorrow.demo.Repository.MedicoRepository;
import projeto_tomorrow.demo.Repository.PacienteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public Consulta criarConsulta(ConsultaDTORequest dto) {
        PerfilMedico medico = medicoRepository.findById(dto.medico().getId())
                .orElseThrow(() -> new NotFoundException("Médico não encontrado"));
        
        PerfilPaciente paciente = pacienteRepository.findById(dto.paciente().getId())
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado"));

        Consulta consulta = new Consulta();
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDataHora(dto.dataHora());
        consulta.setObservacao(dto.observacao());
        consulta.setStatusConsulta(dto.statusConsulta());

        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarTodasConsultas() {
        return consultaRepository.findAll();
    }

    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada"));
    }

    public Consulta atualizarConsulta(Long id, ConsultaDTORequest request) {
        Consulta consulta = buscarPorId(id);
        
        if (request.medico().getId() != null) {
            PerfilMedico medico = medicoRepository.findById(request.medico().getId())
                    .orElseThrow(() -> new NotFoundException("Médico não encontrado"));
            consulta.setMedico(medico);
        }
        
        if (request.paciente().getId() != null) {
            PerfilPaciente paciente = pacienteRepository.findById(request.paciente().getId())
                    .orElseThrow(() -> new NotFoundException("Paciente não encontrado"));
            consulta.setPaciente(paciente);
        }
        
        if (request.dataHora() != null) {
            consulta.setDataHora(request.dataHora());
        }
        
        if (request.observacao() != null) {
            consulta.setObservacao(request.observacao());
        }
        
        if (request.statusConsulta() != null) {
            consulta.setStatusConsulta(request.statusConsulta());
        }

        return consultaRepository.save(consulta);
    }

    public void deletarConsulta(Long id) {
        Consulta consulta = buscarPorId(id);
        consultaRepository.delete(consulta);
    }

    public Consulta confirmarConsulta(Long id) {
        Consulta consulta = buscarPorId(id);
        consulta.setStatusConsulta(StatusConsulta.CONFIRMADA);
        return consultaRepository.save(consulta);
    }

    public Consulta cancelarConsulta(Long id) {
        Consulta consulta = buscarPorId(id);
        consulta.setStatusConsulta(StatusConsulta.CANCELADA);
        return consultaRepository.save(consulta);
    }

    public Consulta concluirConsulta(Long id, String observacao) {
        Consulta consulta = buscarPorId(id);
        consulta.setStatusConsulta(StatusConsulta.CONCLUIDA);
        if (observacao != null && !observacao.trim().isEmpty()) {
            consulta.setObservacao(observacao);
        }
        return consultaRepository.save(consulta);
    }

}
