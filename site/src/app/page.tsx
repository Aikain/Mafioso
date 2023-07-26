import UnderConstruction from '../../public/under_construction.webp';
import styles from './page.module.scss';

const Home = () => (
    <main>
        <div className={styles.underConstruction}>
            <div className={styles.image} style={{ backgroundImage: `url(${UnderConstruction.src})` }}></div>
            <h1>Sivut ovat yhä työnalla!</h1>
        </div>
    </main>
);

export default Home;
