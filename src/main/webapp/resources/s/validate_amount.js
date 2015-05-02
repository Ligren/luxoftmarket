function validate_amount (amount_required, amount_have , submit) {
    valid = true;
    document.getElementById('submit').disabled = 1;

    if ( document.getElementById(amount_have).value < document.getElementById(amount_required).value )
    {
        alert ( "На складе меньше чем требуется Вам !" );
        valid = false;
    }

    return valid;

}