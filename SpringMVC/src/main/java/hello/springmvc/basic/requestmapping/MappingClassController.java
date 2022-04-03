package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    /**
     * 회원 목록 조회: GET /users
     * 회원 등록: POST /users
     * 회원 조회: GET /users/{userId}
     * 회원 수정: PATCH /users/{userId}
     * 회원 삭제: DELETE /users/{userId}
     */

    @RequestMapping(method = RequestMethod.GET)
    public String user(){
        return "get users";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String addUser(){
        return "post users";
    }
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String findUser(@PathVariable String userId){
        return "get userId = "+userId;
    }
    @RequestMapping(value = "/{userId}", method = RequestMethod.PATCH)
    public String updateUser(@PathVariable String userId){
        return "update userId = "+userId;
    }
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable String userId){
        return "delete userId = "+userId;
    }

}
