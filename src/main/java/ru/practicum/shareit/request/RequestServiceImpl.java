package ru.practicum.shareit.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public RequestDto saveRequest(RequestDto requestDto) {
        Request request = RequestMapper.toEntity(requestDto);
        Request savedRequest = requestRepository.save(request);
        return RequestMapper.toDto(savedRequest);
    }

    @Override
    public RequestDto getRequest(Long id) {
        return requestRepository.findById(id)
                .map(RequestMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<RequestDto> getAllRequests() {
        return requestRepository.findAll().stream()
                .map(RequestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }
}
