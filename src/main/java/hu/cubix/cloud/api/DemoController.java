package hu.cubix.cloud.api;

import hu.cubix.cloud.model.DemoResponse;
import hu.cubix.cloud.service.DemoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private final DemoService service;

    private final String defaultMessage;

    public DemoController(DemoService service, @Value("${default-message}") String defaultMessage) {
        this.service = service;
        this.defaultMessage = defaultMessage;
    }

    @GetMapping("/message")
    public DemoResponse demoMessage(@RequestParam(required = false, name = "message") String message) {
        if (!StringUtils.hasText(message)) {
            message = defaultMessage;
        }
        return service.createDemoResponse(message);
    }

}

// Python FastAPI code:
// from fastapi import FastAPI, Query
// from pydantic import BaseModel
// from datetime import date, datetime

// app = FastAPI()

// default_message = "Hello Cloud-native students!"


// class DemoResponse(BaseModel):
//     date: date
//     time: str
//     message: str


// @app.get("/message", response_model=DemoResponse)
// def demo_message(message: str | None = Query(default=None)):
//     if message is None or not message.strip():
//         message = default_message

//     return DemoResponse(
//         date=date.today(),
//         time=datetime.now().time().isoformat(timespec="seconds"),
//         message=message
//     )
