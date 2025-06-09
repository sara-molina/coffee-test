package com.coffee.infrastructure.adapter.output.mapper;


public interface GenericMapper<T, S> {
  T map(S entity);
}
