/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/main/resources/templates/**/*.html',
    './src/input.css',
  ],
 theme: {
      extend: {
        colors: {
          'custom-blue':'#101827',
        }
      },
    },  
  plugins: [],
  darkMode: 'selector'
}
