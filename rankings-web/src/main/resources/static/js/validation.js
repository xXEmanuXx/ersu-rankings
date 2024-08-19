export const validateInput = (input) => {
    input.setCustomValidity('');
    const value = input.value.trim();
    if (value.length != 10 || !value.startsWith("2023", 0)) {
        input.setCustomValidity('Il numero di richiesta deve essere di 10 cifre e iniziare con 2023');
        return false;
    }

    return true;
}