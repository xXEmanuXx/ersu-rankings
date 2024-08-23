'use strict';

document.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById('modal');
    modal.addEventListener('shown.bs.modal', () => {
        modal.focus();
    });
});