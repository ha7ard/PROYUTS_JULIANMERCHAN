<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Cursos</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
                <script src="${pageContext.request.contextPath}/js/app.js"></script>
    </head>

    <body>
        <div class="layout"><aside><a href="dashboard">Dashboard</a><a href="proyuts">PROYUTS</a></aside>
            <main>
                <button type="button" onclick="toggleTheme()" class="small-btn" style="float:right;">Modo claro/oscuro</button>
                    <h1>Cursos en general / Dónde obtener PROYUTS</h1>
                    <p>Recomendados: Dllo App Empresariales, Nuevas Tecnologías Dllo, Programación Web, Aplicaciones Móviles, POO.</p>
                    <form method="post" action="courses" class="card">
                    <input type="hidden" name="action" value="create"/>
                    <input name="name" placeholder="Nombre curso" required/><input name="area" placeholder="Área" required/>
                    <input name="proyutsReward" type="number" placeholder="PROYUTS" required/><input name="schedule" placeholder="Horario" required/>
                <button>Crear curso</button>
                        </form>
                    
                    <input id="courseSearch" placeholder="Buscar curso" onkeyup="searchCourses()"/>
                        <table id="courseTable"><tr><th>Curso</th><th>Área</th><th>PROYUTS</th><th>Horario</th><th>Inscripción</th></tr>
                        <c:forEach var="c" items="${courses}">
<tr><td>${c.name}</td><td>${c.area}</td><td>${c.proyutsReward}</td><td>${c.schedule}</td>
                                <td>
                    <div class="inline-actions">
                    <form method="post" action="courses">
                    <input type="hidden" name="action" value="enroll"/>
                    <input type="hidden" name="courseId" value="${c.id}"/>
                    <button class="small-btn">Registrarse</button>
                    </form>
                    <form method="post" action="courses">
                    <input type="hidden" name="action" value="unenroll"/>
                    <input type="hidden" name="courseId" value="${c.id}"/>
                    <button class="small-btn">Darse de baja</button>
                    </form>
                    </div>
</td></tr>
</c:forEach>
</table>
</main></div>
<script src="assets/js/app.js"></script>
</body>
</html>