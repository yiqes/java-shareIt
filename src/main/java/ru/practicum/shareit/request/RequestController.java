package ru.practicum.shareit.request;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<RequestDto> createRequest(@RequestBody RequestDto requestDto) {
        RequestDto createdRequest = requestService.saveRequest(requestDto);
        return ResponseEntity.ok(createdRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> getRequest(@PathVariable Long id) {
        RequestDto requestDto = requestService.getRequest(id);
        return requestDto != null ? ResponseEntity.ok(requestDto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<RequestDto>> getAllRequests() {
        List<RequestDto> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}
