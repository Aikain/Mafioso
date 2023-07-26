import styles from './page.module.scss';

const Page = () => (
    <div className={styles.page}>
        <h1>Mafioso-sovelluksen tietosuojakäytöntä</h1>
        <p>
            Pitääksemme asiat mahdollisimman yksinkertaisina, varsinkin vielä sovelluksen ollessa kehitysvaiheessa,{' '}
            <b>emme kerää mitään käyttäjätietoja</b>. Kehityksen alkuvaiheessa sovellus kysyy pelaajien nimen, mutta
            tätäkään tietoa ei tallenneta pysyvästi minnekkään vaan on se vain sovelluksen muistissa, ja sen myötä
            katoaa kun sovellus suljetaan kokonaan.
        </p>
        <p>Päivitetty 26.07.2023</p>
    </div>
);

export default Page;
