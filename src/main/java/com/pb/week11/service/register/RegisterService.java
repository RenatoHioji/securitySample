package com.pb.week11.service.register;

import com.pb.week11.DTO.request.LoginDTORequest;
import com.pb.week11.DTO.request.RegisterDTORequest;

public interface RegisterService {
    String register(RegisterDTORequest request);
    String login(LoginDTORequest loginDTORequest);

}
