package pl.wiacekp.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * annotation used for define start class
 *
 * works only in classes implementing pl.wiacekp.command.StartProcess
 *
 * ex. if first vm arg is 'test' then
 * first class with defined @StartProcessFor(arg = "test") will be launched
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface StartProcessFor {
    String arg();
}