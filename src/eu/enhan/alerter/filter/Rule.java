package eu.enhan.alerter.filter;

import eu.enhan.alerter.common.Email;

/**
 * @author Emmanuel Nhan
 */
public class Rule implements FilterRule {

	private final Field operand1;
    private final String operand2;
    private final Operator operator;

    public Rule(Field operand1, Operator operator, String operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }

    @Override
    public boolean satisfy(Email email) {
        switch (operator){
            case CONTENTS:
                return contents(email);
            case ENDS_WITH:
                return endsWith(email);
            case EQUALS:
                return equalsContent(email);
            case STARTS_WITH:
                return startsWith(email);
        }

        return false;
    }

    private String getField(Field field, Email email){
        switch (field){
            case CONTENT:
                return email.getContent();
            case FROM:
                return email.getFrom();
            case SUBJECT:
                return email.getSubject();
            default:
                return "";
        }
    }

    private boolean contents(Email email){
        return getField(operand1, email).contains(operand2);
    }

    private boolean startsWith(Email mail){
        return getField(operand1, mail).startsWith(operand2);
    }

    private boolean endsWith(Email mail){
        return getField(operand1, mail).endsWith(operand2);
    }

    private boolean equalsContent(Email mail){
        return getField(operand1, mail).equals(operand2);
    }




}
