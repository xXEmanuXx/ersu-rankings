'use strict';


document.addEventListener('DOMContentLoaded', () => {
    const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
    const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl, {trigger: 'hover'}));

    const buttons = document.querySelectorAll('.btn-clipboard');
    buttons.forEach((button) => {
        let isProcessing = false;
        button.addEventListener('click', () => {
            if (isProcessing) {
                return;
            }
            isProcessing = true;

            var text = button.parentElement.querySelector('.description').textContent.trim();
            navigator.clipboard.writeText(text).then(() => {
                button.querySelector('img').setAttribute('src', 'icons/check-' + document.documentElement.getAttribute('data-bs-theme') + '.svg');
                setTimeout(() => {
                    button.querySelector('img').setAttribute('src', 'icons/copy-' + document.documentElement.getAttribute('data-bs-theme') + '.svg');
                    isProcessing = false;
                }, 1000);
            }).catch(() => {
                isProcessing = false;
            });
        });
    });
});