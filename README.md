# server-voting-sys-microservices
Voting sys with microservices architecture for SENA headquarters

# Cambios pendientes
1. Cambiar arquitectura a multi-tenant
2. Eliminar entidad de aprendiz:
Tomar unicamente la entidad de usuario y clasificar como aprendiz o funcionario
3. Establecer que tipo de usuario puede acceder a la votacion y que tipo de usuario puede ser candidato
4. Agregar servicio S3 bucket para gestionar imagenes
5. Añadir almacenamiento en cache con redis para candidatos de la votacion actual de cada centro
6. Añadir servicio de comunicación mediante rabbit-mq