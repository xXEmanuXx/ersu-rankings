'use strict';

document.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById('modal');
    modal.addEventListener('shown.bs.modal', () => {
        modal.focus();
    });

    const requestType = document.querySelector('input[name = "request-type"]');
    const tableTitle = document.querySelector('#table-title p');
    if (requestType.value == "pl") {
        if (document.documentElement.lang == "it") {
            tableTitle.textContent += " posto letto";
        }
        else {
            let currentText = tableTitle.textContent;
            tableTitle.textContent = currentText.replace("years ranking", "years accommodation ranking")
        }

        const buttons = document.querySelectorAll('.btn.btn-primary.text-center');
        const rows = document.querySelectorAll('tbody tr');
        buttons.forEach((button) => {
            button.classList.remove('d-none');
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
    }
    else {
        if (document.documentElement.lang == "it") {
            tableTitle.textContent += " borsa studio";
        }
        else {
            let currentText = tableTitle.textContent;
            tableTitle.textContent = currentText.replace("years ranking", "years scholarship ranking")
        }
    }
});