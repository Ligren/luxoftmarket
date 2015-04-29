var min_length=6;
function passValid(form, pass1, pass12, submit)
{
    PASS12=document.getElementById(pass12);
    PASS1count=document.getElementById(pass1).value.length;
    MARG_LEFT= 10*PASS1count-100;
    if(MARG_LEFT<0)
    {
        PASS12.style.marginLeft=MARG_LEFT+"px";
    }
    if(MARG_LEFT>=100)
    {
        PASS12.style.marginLeft="0px";
    }

    if(PASS1count<4){PASS12.style.background="#f00";}
    else if((PASS1count>=4) && (PASS1count<6)){PASS12.style.background="#FF9F00";}
    else if((PASS1count>=6) && (PASS1count<8)){PASS12.style.background="#CBFE01";}
    else if((PASS1count>=8)){PASS12.style.background="#0EFE01";}

}

function isEqual(form,pass1,pass2,pass22,submit)
{
    PASS1=document.getElementById(pass1).value;
    PASS1count=document.getElementById(pass1).value.length;
    PASS2=document.getElementById(pass2).value;
    PASS22=document.getElementById(pass22);
    SUBMIT=document.getElementById('submit');
    if(PASS1==PASS2)
    {
        PASS22.style.border="1px solid #446B01";
        PASS22.style.background="#E0FFB3";
        PASS22.style.color="#558701";
        PASS22.innerHTML="Пароли совпадают";

        if(PASS1count>=min_length)
            SUBMIT.disabled=0;
    }
    else
    {
        PASS22.style.border="1px solid #A40004";
        PASS22.style.background="#FFD7E9";
        PASS22.style.color="#D5172B";
        PASS22.innerHTML="Пароли не совпадают";
        SUBMIT.disabled=1;
    }
}