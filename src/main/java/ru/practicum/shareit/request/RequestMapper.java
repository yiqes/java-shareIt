package ru.practicum.shareit.request;

public class RequestMapper {
    public static RequestDto toDto(Request request) {
        if (request == null) {
            return null;
        }

        RequestDto requestDto = new RequestDto();
        requestDto.setId(request.getId());
        requestDto.setDescription(request.getDescription());
        requestDto.setRequestorId(request.getRequestor().getId());
        return requestDto;
    }

    public static Request toEntity(RequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }

        Request request = new Request();
        request.setId(requestDto.getId());
        request.setDescription(requestDto.getDescription());
        return request;
    }
}

