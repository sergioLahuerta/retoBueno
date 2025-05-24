document.addEventListener('DOMContentLoaded', function () {
    const changeBtn = document.getElementById('change-ingredients-btn');
    const panel = document.getElementById('ingredients-list-panel');
    const choicePannel = document.getElementById('ingredient-choice-panel');

    changeBtn.addEventListener('click', function () {
        if (panel.style.display === 'flex') {
        panel.style.display = 'none';
        } else {
        panel.style.display = 'flex';
        }
        choicePannel.style.display = 'none';
    });
});