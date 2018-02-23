function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}

PrimeFaces.locales['es'] = {
    closeText : 'Cerrar',
    prevText : 'Anterior',
    nextText : 'Siguiente',
    currentText : 'Inicio',
    monthNames : [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre' ],
    monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic' ],
    dayNames : [ 'Domingo', 'Lunes', 'Martes', 'Mi\u00E9rcoles', 'Jueves', 'Viernes', 'S\u00E1bado' ],
    dayNamesShort : [ 'Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab' ],
    dayNamesMin : [ 'Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa' ],
    weekHeader : 'Semana',
    firstDay : 1,
    isRTL : false,
    showMonthAfterYear : false,
    yearSuffix : '',
    timeOnlyTitle : 'S\u00F3lo hora',
    timeText : 'Tiempo',
    hourText : 'Hora',
    minuteText : 'Minuto',
    secondText : 'Segundo',
    currentText : 'Fecha actual',
    ampm : false,
    month : 'Mes',
    week : 'Semana',
    day : 'D\u00EDa',
    allDayText : 'Todo el d\u00EDa'
};