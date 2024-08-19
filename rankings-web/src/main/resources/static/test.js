'use strict';

const getStoredTheme = () => localStorage.getItem('theme')
const setStoredTheme = theme => localStorage.setItem('theme', theme)

const getPreferredTheme = () => {
    const storedTheme = getStoredTheme()
    if (storedTheme) {
        return storedTheme
    }

    return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'
}

const setTheme = theme => {
    if (theme === 'auto') {
        document.documentElement.setAttribute('data-bs-theme', (window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'))
    } 
    else {
        document.documentElement.setAttribute('data-bs-theme', theme)
    }
}

setTheme(getPreferredTheme())

const showActiveTheme = (theme, focus = false) => {
    const themeSwitcher = document.querySelector('#bd-theme')

    if (!themeSwitcher) {
        return
    }

    const activeThemeIcon = document.querySelector('.theme-icon-active')
    const btnToActive = document.querySelector(`[data-bs-theme-value="${theme}"]`)

    document.querySelectorAll('[data-bs-theme-value] img').forEach((img) => {
        let path = img.getAttribute('src')
        let parts = path.split('-')

        if (document.documentElement.getAttribute('data-bs-theme') == 'light') {
            parts[1] = 'light.svg'
        }
        else {
            parts[1] = 'dark.svg'
        }

        let newPath = parts.join('-');
        img.setAttribute('src', newPath);
    })

    const imgOfActiveBtn = btnToActive.querySelector('img').getAttribute('src')

    document.querySelectorAll('[data-bs-theme-value]').forEach(element => {
        element.classList.remove('active')
    })

    btnToActive.classList.add('active')
    activeThemeIcon.setAttribute('src', imgOfActiveBtn)

    if (focus) {
        themeSwitcher.focus()
    }
}

window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', () => {
    const storedTheme = getStoredTheme()
    if (storedTheme !== 'light' && storedTheme !== 'dark') {
        setTheme(getPreferredTheme())
    }
})

window.addEventListener('DOMContentLoaded', () => {
    showActiveTheme(getPreferredTheme())

    document.querySelectorAll('[data-bs-theme-value]').forEach(toggle => {
        toggle.addEventListener('click', () => {
            const theme = toggle.getAttribute('data-bs-theme-value')
            setStoredTheme(theme)
            setTheme(theme)
            showActiveTheme(theme, true)
        })
    })
})

document.addEventListener('DOMContentLoaded', function () {
    const buttons = document.querySelectorAll('.btn.btn-primary');
    const forms = document.querySelectorAll('form', '.needs-validation');
    
    // function to show different forms based on button click
    buttons.forEach((button, btnIndex) => {
        button.addEventListener('click', function () {
            forms.forEach((form, formIndex) => { 
                if (btnIndex == formIndex) {
                    form.style.display = 'block';
                }
                else {
                    form.style.display = 'none';
                }
            })

            document.getElementById('form' + (btnIndex + 1)).parentElement.classList.remove('d-none');
        })
    })

    function validateInput(input) {
        input.setCustomValidity('');
        const value = input.value.trim();
        if (value.length != 10 || !value.startsWith("2023", 0)) {
            input.setCustomValidity('Il numero di richiesta deve essere di 10 cifre e iniziare con 2023');
            return false;
        }

        return true;
    }

    // validation function for each form
    forms.forEach((form, index) => {
        form.addEventListener('submit', event => {

            if (form.id == 'form1') {
                const inputFeedback = document.querySelector('#form1-request-number + .invalid-feedback')
                const input = form.querySelector('[name = "request-number"]');
                
                if (!validateInput(input)) {
                    event.preventDefault();
                    event.stopPropagation();

                    inputFeedback.textContent = input.validationMessage;
                }
            }
            else {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
            }

            form.classList.add('was-validated');

            const radioButtonsRequestType = form.querySelectorAll('input[name = "request-type"]');
            const radioButtonsYearType = form.querySelectorAll('input[name = "year-type"]');
            
            const radioFeedback = form.querySelector('#form' + (index + 1) + '-request-type-feedback')
            if (!Array.from(radioButtonsRequestType).some(rb => rb.checked)) {
                radioFeedback.style.display = 'block';
                event.preventDefault();
                event.stopPropagation();
            }
            else {
                radioFeedback.style.display = 'none';
            }

            if (form.id == 'form2') {
                const radioFeedback = form.querySelector('#form' + (index + 1) + '-year-type-feedback')
                if (!Array.from(radioButtonsYearType).some(rb => rb.checked)) {
                    radioFeedback.style.display = 'block';
                    event.preventDefault();
                    event.stopPropagation();
                }
                else {
                    radioFeedback.style.display = 'none';
                }
            }
        }, false)
    })

    // check every time a radio button is pressed in form2 if each group has been selected and do something based on which button was pressed
    
    function handleSelectChange(nextElement) {
        if (nextElement !== null) {
            nextElement.removeAttribute('disabled');
        }
    }

    function yearCheck() {
        const form = document.getElementById('form2');
        const radioButtonsRequestType = form.querySelectorAll('input[name = "request-type"]');
        
        const fyRadio = document.getElementById('form2-year-type-fy');
        const syRadio = document.getElementById('form2-year-type-sy');

        const cdlSelect = form.querySelector('[name = "cdl"]');
        const cdlTypeSelect = form.querySelector('[name = "cdl-type"]');
        const yearSelect = form.querySelector('[name = "year"]');
        
        if (fyRadio.checked) {
            form.querySelector('.row.mt-4').style.display = 'none';
            form.querySelector('.col-12.text-center.h5.my-5').classList.remove('d-none');

            cdlSelect.removeAttribute('required', '');
            cdlTypeSelect.removeAttribute('required', '');
            yearSelect.removeAttribute('required', '');
        }

        if (syRadio.checked) {
            form.querySelector('.row.mt-4').style.display = 'flex';
            form.querySelector('.col-12.text-center.h5.my-5').classList.add('d-none');

            cdlSelect.setAttribute('required', '');
            cdlTypeSelect.setAttribute('required', '');
            yearSelect.setAttribute('required', '');

            if (Array.from(radioButtonsRequestType).some(rb => rb.checked)) {
                cdlSelect.removeAttribute('disabled');

                cdlSelect.addEventListener('change', () => handleSelectChange(cdlTypeSelect));
                cdlTypeSelect.addEventListener('change', () => handleSelectChange(yearSelect));
                yearSelect.addEventListener('change', () => handleSelectChange(null));
            }

        }
    }

    const radios = document.getElementById('form2').querySelectorAll('input[type = "radio"]');
    radios.forEach((radio) => {
        radio.addEventListener('click', () => yearCheck());
    })

    // theme selection
    
})