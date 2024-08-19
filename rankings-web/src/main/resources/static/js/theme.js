const getStoredTheme = () => localStorage.getItem('theme');
export const setStoredTheme = (theme) => localStorage.setItem('theme', theme);


export const getPreferredTheme = () => {
    const storedTheme = getStoredTheme();
    if (storedTheme) {
        return storedTheme;
    }

    return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light';
}

export const setTheme = (theme) => {
    if (theme === 'auto') {
        document.documentElement.setAttribute('data-bs-theme', (window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'));
    } 
    else {
        document.documentElement.setAttribute('data-bs-theme', theme);
    }
};

export const showActiveTheme = (theme, focus = false) => {
    const themeSwitcher = document.querySelector('#bd-theme');

    if (!themeSwitcher) {
        return;
    }

    const activeThemeIcon = document.querySelector('.theme-icon-active');
    const btnToActive = document.querySelector(`[data-bs-theme-value="${theme}"]`);

    document.querySelectorAll('[data-bs-theme-value] img').forEach((img) => {
        let path = img.getAttribute('src');
        let parts = path.split('-');

        if (document.documentElement.getAttribute('data-bs-theme') == 'light') {
            parts[1] = 'light.svg';
        }
        else {
            parts[1] = 'dark.svg';
        }

        let newPath = parts.join('-');
        img.setAttribute('src', newPath);
    });

    const imgOfActiveBtn = btnToActive.querySelector('img').getAttribute('src');

    document.querySelectorAll('[data-bs-theme-value]').forEach(element => {
        element.classList.remove('active');
    });

    btnToActive.classList.add('active');
    activeThemeIcon.setAttribute('src', imgOfActiveBtn)

    if (focus) {
        themeSwitcher.focus();
    }
};