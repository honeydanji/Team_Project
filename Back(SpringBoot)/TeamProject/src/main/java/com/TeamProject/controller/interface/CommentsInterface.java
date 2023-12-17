package com.TeamProject.Controller.Interface;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface CommentsInterface {

    ResponseEntity<String> commentUpload(@PathVariable int historyId,
                                        @RequestParam String contents,
                                        Authentication authentication);

    ResponseEntity<String> commentUpdate(@PathVariable int historyId,
                                         @RequestParam String contents,
                                         Authentication authentication);

    ResponseEntity<String> commentDelete(@PathVariable int historyId,
                                         Authentication authentication);
}
