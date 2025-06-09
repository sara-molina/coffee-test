package com.coffee.infrastructure.adapter.output.repository.json;

import com.coffee.infrastructure.adapter.output.mapper.GenericMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;

public abstract class GenericRepository<T, S> {
  public List<T> findAll(
      Resource resource, ObjectMapper objectMapper, GenericMapper<T, S> mapper, Class<S> sClass) {
    try (InputStream is = resource.getInputStream()) {
      List<S> result =
          objectMapper.readValue(
              is, objectMapper.getTypeFactory().constructCollectionType(List.class, sClass));
      return result.stream().map(mapper::map).collect(Collectors.toList());
    } catch (Exception e) {
      throw new RuntimeException("Error reading resource", e);
    }
  }
}
