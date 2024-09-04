'use strict';

document.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById('modal');
    modal.addEventListener('shown.bs.modal', () => {
        modal.focus();
    });

    const buttons = document.querySelectorAll('.btn.btn-primary.text-center');
    const rows = document.querySelectorAll('tbody tr');
    buttons.forEach((button) => {
        button.addEventListener('click', () => {
            var max = (button.value) ? Math.floor(rows.length * button.value / 100) < 1 ? 1 : Math.floor(rows.length * button.value / 100) : 200;
            for (let i = 0; i < max; i++) {
                rows[i].classList.add('highlight-success');
                rows[i].classList.remove('highlight-danger');
            }
            for (let i = max; i < rows.length; i++) {
                rows[i].classList.add('highlight-danger');
                rows[i].classList.remove('highlight-success');
            }
        })
    });
});