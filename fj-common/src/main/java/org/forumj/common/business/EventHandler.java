package org.forumj.common.business;

/**
 * Created by Andrew on 26/02/2017.
 */
public interface EventHandler {

    boolean isAsync();
    boolean handle(Event event);
}
