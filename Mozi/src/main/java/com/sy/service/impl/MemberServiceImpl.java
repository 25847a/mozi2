package com.sy.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sy.mapper.MemberMapper;
import com.sy.pojo.Member;
import com.sy.service.MemberService;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {


	/*@Autowired
	private MemberMapper memberMapper;*/

}
