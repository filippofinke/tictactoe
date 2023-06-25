package ch.supsi.tictactoe.persistence;


import ch.supsi.tictactoe.exceptions.FailedToLoadGameException;
import ch.supsi.tictactoe.exceptions.FailedToSaveGameException;
import ch.supsi.tictactoe.gamelogic.Move;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class GameFilePersistenceManager implements GamePersistenceManager {
    @Override
    public void save(Path destination, List<Move> moves) throws FailedToSaveGameException {
        File file = destination.toFile();
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
        ) {
            oos.writeObject(moves);
        } catch (IOException e) {
            throw new FailedToSaveGameException(e.getMessage());
        }
    }

    @Override
    public List<Move> load(Path source) throws FailedToLoadGameException {


        File file = source.toFile();

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            @SuppressWarnings("unchecked")
            List<Move> loadedMoves = (List<Move>) ois.readObject();

            return loadedMoves;
        } catch (IOException | ClassNotFoundException e) {
            throw new FailedToLoadGameException(e.getMessage());
        }

    }
}
