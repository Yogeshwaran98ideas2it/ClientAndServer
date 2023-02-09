package com.example.healthcare.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomModelMapping.
 */
@Component
public class CustomModelMapping extends ModelMapper {

	 /**
 	 * Map list.
 	 *
 	 * @param <D> the generic type
 	 * @param source the source
 	 * @param destinationType the destination type
 	 * @return the list
 	 */
 	public <D> List<D> mapList(List<? extends Object> source, Class<D> destinationType) {
		 List<D> tempSource=new ArrayList<D>();
		 tempSource=source
		 .stream()
		 .map(element-> super
		 .map(element,destinationType))
		 .collect(Collectors.toList());
		return tempSource;
	 }
	 
}
