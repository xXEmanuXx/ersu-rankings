'use strict';

document.addEventListener('DOMContentLoaded', () => {
    const index = document.getElementById('tab-index');
    const contents = document.querySelector('.tab-content');
    
    if (window.innerWidth < 500) {
        index.classList.remove('nav-tabs');
        index.classList.add('nav-pills');
        contents.classList.add('border-top', 'border-secondary-custom', 'pt-3');
    }

    window.addEventListener('resize', () => {
        if (window.innerWidth < 500) {
            index.classList.remove('nav-tabs');
            index.classList.add('nav-pills');
            contents.classList.add('border-top', 'border-secondary-custom', 'pt-3');
        } else {
            index.classList.remove('nav-pills');
            index.classList.add('nav-tabs');
            contents.classList.remove('border-top', 'border-secondary-custom', 'pt-3');
        }
    });
});