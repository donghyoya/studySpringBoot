//package com.gogofnd.theCreditableCMS.global.application;
//
//import com.gogofnd.theCreditableCMS.domain.user.entity.User;
//import com.gogofnd.theCreditableCMS.domain.user.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service
//public class CustomUserDetailService implements UserDetailsService{
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String riderId) throws UsernameNotFoundException {
//
//        User user = userRepository.findById(Long.valueOf(riderId)).orElse(null);
//
//        return user;
//    }
//}
// 불필요함