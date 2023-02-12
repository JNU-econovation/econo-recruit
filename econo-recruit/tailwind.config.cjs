/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {},
  plugins: [require('tailwind-scrollbar-hide'), require('@tailwindcss/line-clamp')],
}
