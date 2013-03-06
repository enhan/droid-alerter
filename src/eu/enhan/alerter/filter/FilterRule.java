package eu.enhan.alerter.filter;

import eu.enhan.alerter.common.Email;

/**
 * @author Emmanuel Nhan
 */
public interface FilterRule {

    /**
     * Depending on the email param, returns true or false
     * @param email
     * @return
     */
    boolean satisfy(Email email);

}
