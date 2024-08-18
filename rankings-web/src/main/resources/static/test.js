document.addEventListener('DOMContentLoaded', function () {
    const buttons = document.querySelectorAll('.btn.btn-danger');
    const forms = document.querySelectorAll('form');

    buttons.forEach((button, btnIndex) => {
        button.addEventListener('click', function () {
            forms.forEach((form, formIndex) => { 
                if (btnIndex !== formIndex) {
                    form.style.display = 'none';
                }
                else {
                    form.style.display = 'block';
                }
            })

            document.getElementById('form' + (btnIndex + 1)).parentElement.classList.remove('d-none');
        })
    })
})