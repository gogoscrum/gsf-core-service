package com.shimi.gsf.util;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * SnowflakeId is a custom annotation used to mark an entity field as a Snowflake ID.
 */
@IdGeneratorType(SnowflakeIdGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface SnowflakeId {
}
