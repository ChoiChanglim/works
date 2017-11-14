package com.nhn.base.constant.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * TilesOn 어노테이션으로 타일즈 적용하기
 * 발동 조건  1. TilesOn 어노테이션 작성.
 *        2. nhn 확장자일때 적용됨
 * @author WOW
 *
 */
public @interface TilesOn {

}
