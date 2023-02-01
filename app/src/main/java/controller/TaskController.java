package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

public class TaskController {

    public void save(Task task) {

        String sql = "INSERT INTO task(idProject,"
                + "name,"
                + "description,"
                + "completed,"
                + "notes,"
                + "deadline,"
                + "createdDate,"
                + "uptadedDate) VALUES (?,?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatDate().getTime()));
            statement.setDate(8, new Date(task.getUpdatedDate().getTime()));
           
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a tarefa" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void update(Task task) {
        String sql = "UPDATE task SET idProject = ?, name = ?, description = ?, notes = ?, completed = ?, deadline = ?, createdDate = ?, uptadedDate = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
//            Estabelecendo a conexao com banco de dados
            connection = ConnectionFactory.getConnection();
//            preparando a query
            statement = connection.prepareStatement(sql);

//           setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isIsCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatDate().getTime()));
            statement.setDate(8, new Date(task.getUpdatedDate().getTime()));
            statement.setInt(9, task.getId());
            
//            executando a query
            statement.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ataualizar a tarefa" + e.getMessage());
        }finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void removeById(int taskId){

        String sql = "DELETE FROM task WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
//            criaçao da conexao com banco de dados
            connection = ConnectionFactory.getConnection();
//          preparando a query            
            statement = connection.prepareStatement(sql);
//            setando os valores
            statement.setInt(1, taskId);
//            executando a query
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro a deletar a tarefa" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public List<Task> getAll(int idProject) {
        String sql = "SELECT * FROM task WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
//        lista de tarefas, devolvendo quando a chamado do metodo acontecer
        List<Task> tasks = new ArrayList<Task>();
        
        try {
//            criando conexao
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
//            setando o valor que corresponde ao filtro de busca
            statement.setInt(1, idProject);
            
//            valor retornado pela execuçao da query
            resultSet = statement.executeQuery();
            
//            enquanto houverem valores a serem percorridos meu resulset
            while(resultSet.next()){
                
                Task task = new Task();
                task.setId(resultSet.getInt("id"));  
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatDate(resultSet.getDate("createdDate"));
//                task.setUpdatedDate(resultSet.getDate("updatedDate"));
                
                tasks.add(task);
            }
            
        } 
        catch (Exception e) {
            throw new RuntimeException("Erro ao inserir a tarefa a tarefa" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement,resultSet);
        }
//        lista de tarefas criadas e carregada do banco de dados
        return tasks;
    }
}
