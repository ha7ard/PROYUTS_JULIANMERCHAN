function filterTable() {
  const input = document.getElementById('filter');
  const term = input.value.toLowerCase();
  document.querySelectorAll('#proyutTable tr').forEach((row, i) => {
    if (i === 0) return;
    row.style.display = row.children[1].textContent.toLowerCase().includes(term) ? '' : 'none';
  });
}
function searchCourses() {
  const term = document.getElementById('courseSearch').value.toLowerCase();
  document.querySelectorAll('#courseTable tr').forEach((row, i) => {
    if (i === 0) return;
    row.style.display = row.children[0].textContent.toLowerCase().includes(term) ? '' : 'none';
  });
}
(function initTheme() {
  const saved = localStorage.getItem('proyuts-theme') || 'light';
  document.documentElement.setAttribute('data-theme', saved);
})();
function toggleTheme() {
  const current = document.documentElement.getAttribute('data-theme') || 'light';
  const next = current === 'light' ? 'dark' : 'light';
  document.documentElement.setAttribute('data-theme', next);
  localStorage.setItem('proyuts-theme', next);
}
