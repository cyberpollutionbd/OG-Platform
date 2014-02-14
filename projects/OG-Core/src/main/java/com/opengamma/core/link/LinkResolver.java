/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.link;

/**
 * Responsible for actually performing the resolve operation
 * for a {@link Link} instance.
 *
 * @param <T> the type of object provided by the resolver
 */
public interface LinkResolver<T> {

  /**
   * Resolves the contents of a Link returning a concrete
   * instance of the required type.
   *
   * @return an instance of the type a Link actually points to
   */
  T resolve();
}