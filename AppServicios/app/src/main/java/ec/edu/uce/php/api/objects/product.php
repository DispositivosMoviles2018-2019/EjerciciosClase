<?php
class Product
{
 
    // database connection and table name
    private $conn;
    private $table_name = "usuario";
 
    // object properties
    public $id;
    //public $name;
    //public $description;
    //public $price;
    //public $category_id;
    //public $category_name;
    //public $created;
    public $nombre;
    public $apellido;
    public $edad;
 
    // constructor with $db as database connection
    public function __construct($db)
    {
        $this->conn = $db;
    }

    // read products
    function read()
    {
 
    // select all query
        $query = "SELECT * FROM usuario";
 
    // prepare query statement
        $stmt = $this->conn->prepare($query);
 
    // execute query
        $stmt->execute();

        return $stmt;
    }

    // create product
    function create()
    {
 
    // query to insert record
        $query = "INSERT INTO
                " . $this->table_name . "
            SET
                nombre=:nombre, apellido=:apellido, edad=:edad";
               
 
    // prepare query
        $stmt = $this->conn->prepare($query);
 
    // sanitize
        $this->nombre = htmlspecialchars(strip_tags($this->nombre));
        $this->apellido = htmlspecialchars(strip_tags($this->apellido));
        $this->edad = htmlspecialchars(strip_tags($this->edad));
        $this->category_id = htmlspecialchars(strip_tags($this->category_id));
        $this->created = htmlspecialchars(strip_tags($this->created));
 
    // bind values
        $stmt->bindParam(":nombre", $this->nombre);
        $stmt->bindParam(":apellido", $this->apellido);
        $stmt->bindParam(":edad", $this->edad);

 
    // execute query
        if ($stmt->execute()) {
            return true;
        }

        return false;

    }

// used when filling up the update product form
    function readOne()
    {
 
    // query to read single record
        $query = "SELECT * FROM" . $this->table_name . " u WHERE u.id = ? LIMIT 0,1";
 
    // prepare query statement
        $stmt = $this->conn->prepare($query);
 
    // bind id of product to be updated
        $stmt->bindParam(1, $this->id);
 
    // execute query
        $stmt->execute();
 
    // get retrieved row
        $row = $stmt->fetch(PDO::FETCH_ASSOC);
 
    // set values to object properties
        $this->nombre = $row['nombre'];
        $this->apellido = $row['apellido'];
        $this->edad = $row['edad'];
    }

    // update the product
    function update()
    {
 
    // update query
        $query = "UPDATE
                " . $this->table_name . "
            SET
                nombre = :nombre,
                apellido = :apellido,
                edad = :edad
            WHERE
                id = :id";
 
    // prepare query statement
        $stmt = $this->conn->prepare($query);
 
    // sanitize
        $this->nombre = htmlspecialchars(strip_tags($this->nombre));
        $this->apellido = htmlspecialchars(strip_tags($this->apellido));
        $this->edad = htmlspecialchars(strip_tags($this->edad));
        $this->id = htmlspecialchars(strip_tags($this->id));
 
    // bind new values
        $stmt->bindParam(':nombre', $this->nombre);
        $stmt->bindParam(':apellido', $this->apellido);
        $stmt->bindParam(':edad', $this->edad);
        $stmt->bindParam(':id', $this->id);
 
    // execute the query
        if ($stmt->execute()) {
            return true;
        }

        return false;
    }

    // delete the product
    function delete()
    {
 
    // delete query
        $query = "DELETE FROM " . $this->table_name . " WHERE id = ?";
 
    // prepare query
        $stmt = $this->conn->prepare($query);
 
    // sanitize
        $this->id = htmlspecialchars(strip_tags($this->id));
 
    // bind id of record to delete
        $stmt->bindParam(1, $this->id);
 
    // execute query
        if ($stmt->execute()) {
            return true;
        }

        return false;

    }

}