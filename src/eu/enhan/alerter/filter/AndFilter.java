package eu.enhan.alerter.filter;

import eu.enhan.alerter.common.Email;

import java.util.Iterator;
import java.util.Set;

/**
 * @author Emmanuel Nhan
 */
class AndFilter extends Filter{

    AndFilter(Set<FilterRule> rules, ActionTemplate actionTemplate) {
        super(rules, actionTemplate);
    }

    @Override
    public boolean matches(Email email) {

        Iterator<FilterRule> it = rules.iterator();
        while (it.hasNext()){
            if (!it.next().satisfy(email)){
                 return false;
            }
        }

        return true;
    }


}
