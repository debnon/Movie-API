package com.MovieAPI.service;


import com.MovieAPI.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService  {

    @Autowired
    com.MovieAPI.repository.MovieRepository MovieRepository;


}
