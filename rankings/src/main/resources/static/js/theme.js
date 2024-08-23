'use strict';

const getStoredTheme = () => localStorage.getItem('theme');
const setStoredTheme = (theme) => localStorage.setItem('theme', theme);

const getPreferredTheme = () => {
    const storedTheme = getStoredTheme();
    if (storedTheme) {
        return storedTheme;
    }

    return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light';
}

const setTheme = (theme) => {
    if (theme === 'auto') {
        document.documentElement.setAttribute('data-bs-theme', (window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'));
    } 
    else {
        document.documentElement.setAttribute('data-bs-theme', theme);
    }
};

setTheme(getPreferredTheme());

const showActiveTheme = (theme, focus = false) => {
    const themeSwitcher = document.querySelector('#bd-theme');

    if (!themeSwitcher) {
        return;
    }
    
    // change icons in the theme selector
    document.querySelectorAll('img[src*="-"]').forEach((img) => {
        var path = img.getAttribute('src');
        var parts = path.split('-');

        if (document.documentElement.getAttribute('data-bs-theme') == 'light') {
            parts[1] = 'light.svg';
        }
        else {
            parts[1] = 'dark.svg';
        }

        var newPath = parts.join('-');
        img.setAttribute('src', newPath);
    });
    
    const activeThemeIcon = document.querySelector('.theme-icon-active');
    const btnToActive = document.querySelector(`[data-bs-theme-value="${theme}"]`);
    const imgOfActiveBtn = btnToActive.querySelector('img').getAttribute('src');

    document.querySelectorAll('[data-bs-theme-value]').forEach(item => {
        item.classList.remove('active');
    });

    btnToActive.classList.add('active');
    activeThemeIcon.setAttribute('src', imgOfActiveBtn);

    if (focus) {
        themeSwitcher.focus();
    }
};

window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', () => {
    const storedTheme = getStoredTheme();
    if (storedTheme !== 'light' && storedTheme !== 'dark') {
        setTheme(getPreferredTheme());
    }
});

window.addEventListener('DOMContentLoaded', () => {
    showActiveTheme(getPreferredTheme());
    
    document.querySelectorAll('[data-bs-theme-value]').forEach((toggle) => {
        toggle.addEventListener('click', () => {
            const theme = toggle.getAttribute('data-bs-theme-value');
            setStoredTheme(theme);
            setTheme(theme);
            showActiveTheme(theme, true);
        });
    });
});
