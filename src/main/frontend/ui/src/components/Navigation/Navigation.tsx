import React from 'react';
import './NavigationStyle.scss';
import * as data from './links.json';
import { NavLink } from "react-router-dom";
const linksString = JSON.stringify(data);
const links = JSON.parse(linksString).links;

type Link = {
    label: string;
    href: string;
};

const Links: React.FC<{ links: Link[] }> = ({ links }) => {
    return (
        <div className={'links-container'}>
            {links.map((link: Link) => {
                return (
                    <div key={link.href} className={'link'}>
                        <NavLink to={link.href} className="card-link">
                            {link.label}
                        </NavLink>
                    </div>
                )
            })}
        </div>
    )
};


const Nav: React.FC<{}> = () => {
    return (
        <nav className={'navbar'}>
            <div className={'logo-container'}>
                <NavLink to={"/info"} className="card-link">
                    {"Информация"}
                </NavLink>
            </div>

            <Links links={links} />
        </nav>
    )
}

export default Nav;
