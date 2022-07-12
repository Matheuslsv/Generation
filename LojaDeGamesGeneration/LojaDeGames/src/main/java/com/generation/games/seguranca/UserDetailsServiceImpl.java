package com.generation.games.seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.games.model.Usuario;
import com.generation.games.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository UserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Usuario> user = UserRepository.findByUsuario(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + " Usuario não encontrado!"));
		
		return user.map(UserDetailsImpl::new).get();
	}
}
