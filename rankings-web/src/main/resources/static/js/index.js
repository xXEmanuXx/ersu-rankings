'use strict';

const validateInput = (input) => {
    input.setCustomValidity('');
    const value = input.value.trim();
    if (value.length != 10 || !value.startsWith("2023", 0)) {
        input.setCustomValidity('Il numero di richiesta deve essere di 10 cifre e iniziare con 2023');
        return false;
    }

    return true;
}

document.addEventListener('DOMContentLoaded', () => {
    const buttons = document.querySelectorAll('.btn.btn-primary');
    const forms = document.querySelectorAll('form', '.needs-validation');
    
    // show different forms based on button click
    buttons.forEach((button, btnIndex) => {
        button.addEventListener('click', () => {
            forms.forEach((form, formIndex) => { 
                if (btnIndex == formIndex) {
                    form.style.display = 'block';
                }
                else {
                    form.style.display = 'none';
                }
            });

            document.getElementById('form' + (btnIndex + 1)).parentElement.classList.remove('d-none');
        });
    });

    // validation for each form
    forms.forEach((form, index) => {
        form.addEventListener('submit', (event) => {
            if (form.id == 'form1') {
                const inputFeedback = document.querySelector('#form1-request-number + .invalid-feedback');
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

            const radioButtonsRequestType = form.querySelectorAll('input[name = "request-type"]');
            const radioButtonsYearType = form.querySelectorAll('input[name = "year-type"]');
            
            const radioFeedback = form.querySelector('#form' + (index + 1) + '-request-type-feedback');
            if (!Array.from(radioButtonsRequestType).some(rb => rb.checked)) {
                radioFeedback.style.display = 'block';
                event.preventDefault();
                event.stopPropagation();
            }
            else {
                radioFeedback.style.display = 'none';
            }

            if (form.id == 'form2') {
                const radioFeedback = form.querySelector('#form' + (index + 1) + '-year-type-feedback');
                if (!Array.from(radioButtonsYearType).some(rb => rb.checked)) {
                    radioFeedback.style.display = 'block';
                    event.preventDefault();
                    event.stopPropagation();
                }
                else {
                    radioFeedback.style.display = 'none';
                }
            }

            form.classList.add('was-validated');
        }, false);
    });

    // show different form parts based on radio clicked on form2
    const handleSelectChange = (nextElement) => {
        nextElement.removeAttribute('disabled');
    }

    const radios = document.getElementById('form2').querySelectorAll('input[type = "radio"]');
    radios.forEach((radio) => {
        radio.addEventListener('click', () => {
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
                }
            }
        });
    });
});