function swapThemeIcon(newTheme) {
  const el = document.querySelector(".theme-switch");
  let content;

  if (newTheme === "dark") {
    content =
      '<circle r="5" cy="12" cx="12"></circle><path d="M12 1v2M12 21v2M4.22 4.22l1.42 1.42M18.36 18.36l1.42 1.42M1 12h2M21 12h2M4.22 19.78l1.42-1.42M18.36 5.64l1.42-1.42"></path>';
  } else {
    content = '<path d="M21 12.79A9 9 0 1111.21 3 7 7 0 0021 12.79z" />';
  }

  if (el) {
    el.innerHTML = `<svg stroke="currentColor" fill="none" stroke-linejoin="round" width="24" shape-rendering="geometricPrecision" stroke-linecap="round" stroke-width="1.5" viewBox="0 0 24 24" height="24">${content}</svg>`;
  }
}

function changeTheme(newTheme) {
  const curr = document.body.className;
  window.localStorage.setItem("theme", newTheme);

  if (curr !== newTheme) {
    document.body.className = newTheme;
    swapThemeIcon(newTheme);
  }
}

function switchTheme() {
  let theme = window.localStorage.getItem("theme");

  if (!theme) {
    theme = document.body.className || "light";
  }

  if (theme === "dark") {
    changeTheme("light");
  } else {
    changeTheme("dark");
  }
}

function isSystemDark() {
  return (
    window.matchMedia &&
    window.matchMedia("(prefers-color-scheme: dark)").matches
  );
}

window.onload = function () {
  const dark = isSystemDark();
  const theme = window.localStorage.getItem("theme");

  if (theme) {
    changeTheme(theme);
  } else {
    changeTheme(dark ? "dark" : "light");
  }
};
