'use strict';

const validateInput = (input) => {
    input.setCustomValidity('');
    const value = input.value.trim();
    if (value.length != 10 || !value.startsWith("2023", 0) || isNaN(value)) {
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
    const createJsonData = (...data) => {
        let jsonData = {};

        for (let i = 0; i < data.length; i++) {
            const [key, value] = data[i];
            jsonData[key] = value;
        }

        return jsonData;
    }

    const getSelectData = (data, address) => {
        return fetch('/api/get' + address, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
        .then((response) => {
            return response.json();
        })
        .then((options) => {
            return options;
        })
        .catch((error) => {
            console.error('Error fetching data:', error);
            return null;
        });
    }

    const setSelectData = (options, select_type) => {
        if (options) {
            let selectElement = document.getElementById('form2-' + select_type);
            while (selectElement.options.length > 1) {
                selectElement.remove(1);
            }
            selectElement.selectedIndex = 0;

            options.forEach((option) => {
                const optionElement = document.createElement('option');
                optionElement.value = option;
                optionElement.textContent = option;
                selectElement.appendChild(optionElement);
            });
        }
        else {
            console.error("Bad request value" + options);
        }
    }

    const form = document.getElementById('form2');
    const radios = form.querySelectorAll('input[type = "radio"]');
    const cdlNameSelect = form.querySelector('[name = "cdl-name"]');
    const cdlTypeSelect = form.querySelector('[name = "cdl-type"]');
    const yearSelect = form.querySelector('[name = "year"]');

    var previousGroup1State = null;
    var bsRadio = document.getElementById('form2-request-type-bs');
    var plRadio = document.getElementById('form2-request-type-pl');
    
    var previousGroup2State = null;
    var fyRadio = document.getElementById('form2-year-type-fy');
    var syRadio = document.getElementById('form2-year-type-sy');

    var requestType;
    var cdlName;
    var cdlType;
    var shouldSend = false;

    radios.forEach((radio) => {
        radio.addEventListener('click', () => {
            // dont show and dont require selects
            if (fyRadio.checked && previousGroup2State !== fyRadio.value) {
                previousGroup2State = fyRadio.value;

                form.querySelector('.row.mt-4').style.display = 'none';
                form.querySelector('.col-12.text-center.my-5').classList.remove('d-none');

                cdlNameSelect.removeAttribute('required', '');
                cdlTypeSelect.removeAttribute('required', '');
                yearSelect.removeAttribute('required', '');
            }

            // show and require selects
            if (syRadio.checked && previousGroup2State !== syRadio.value) {
                previousGroup2State = syRadio.value;

                form.querySelector('.row.mt-4').style.display = 'flex';
                form.querySelector('.col-12.text-center.my-5').classList.add('d-none');

                cdlNameSelect.setAttribute('required', '');
                cdlTypeSelect.setAttribute('required', '');
                yearSelect.setAttribute('required', '');
            }

            // send request for cdlNameSelect data

            if ((bsRadio.checked || plRadio.checked) && syRadio.checked) {
                if (bsRadio.checked && previousGroup1State !== bsRadio.value && previousGroup2State === syRadio.value) {
                    previousGroup1State = bsRadio.value;
                    requestType = bsRadio.value;
                    shouldSend = true;
                }
                if (plRadio.checked && (previousGroup1State !== plRadio.value && previousGroup2State === syRadio.value)) {
                    previousGroup1State = plRadio.value;
                    requestType = plRadio.value;
                    shouldSend = true;
                }

                if (shouldSend) {
                    cdlNameSelect.setAttribute('disabled', '');
                    cdlNameSelect.selectedIndex = 0;
                    cdlTypeSelect.setAttribute('disabled', '');
                    cdlTypeSelect.selectedIndex = 0;
                    yearSelect.setAttribute('disabled', '');
                    yearSelect.selectedIndex = 0;

                    let json = createJsonData(["requestType", requestType]);
                    getSelectData(json, "CdlName").then((options) => {
                        setSelectData(options, "cdl-name");
                    });
                    cdlNameSelect.removeAttribute('disabled');
                    shouldSend = false;
                }
            }
        })
    });

    cdlNameSelect.addEventListener('change', () => {
        cdlName = cdlNameSelect.value;
        let json = createJsonData(["requestType", requestType], ["cdlName", cdlName]);
        getSelectData(json, "CdlType").then((options) => {
            setSelectData(options, "cdl-type");
        });
        cdlTypeSelect.removeAttribute('disabled');
    });

    cdlTypeSelect.addEventListener('change', () => {
        cdlType = cdlTypeSelect.value;
        let json = createJsonData(["requestType", requestType], ["cdlName", cdlName], ["cdlType", cdlType]);
        getSelectData(json, "Year").then((options) => {
            setSelectData(options, "year");
        });
        yearSelect.removeAttribute('disabled');
    });
});