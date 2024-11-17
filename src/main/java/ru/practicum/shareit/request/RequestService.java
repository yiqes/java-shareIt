package ru.practicum.shareit.request;

import java.util.List;

public interface RequestService {

    RequestDto saveRequest(RequestDto requestDto);

    RequestDto getRequest(Long id);

    List<RequestDto> getAllRequests();

    void deleteRequest(Long id);
}
