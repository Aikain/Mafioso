import type { Metadata } from 'next';
import { ReactNode } from 'react';

import './globals.css';

export const metadata: Metadata = {
    title: 'Mafioso',
    description: 'Mafioso-seurapeli puhelimen avulla pelattavaksi',
};

interface Props {
    children: ReactNode;
}

const RootLayout = ({ children }: Props) => (
    <html lang='fi'>
        <body>{children}</body>
    </html>
);
export default RootLayout;
