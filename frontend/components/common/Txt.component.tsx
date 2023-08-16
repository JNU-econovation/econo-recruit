import { FC, PropsWithChildren } from "react";
import classNames from "classnames";

const typographyType = {
  h1: "text-4xl font-bold",
  h2: "text-3xl font-bold",
  h3: "text-2xl font-bold",
  h4: "text-xl font-bold",
  h5: "text-lg font-bold",
  h6: "text-base font-bold",
  p: "text-base",
};

const colorType = {
  black: "text-black",
  gray: "text-gray-500",
  white: "text-white",
};

interface TxtProps {
  typography?: keyof typeof typographyType;
  color?: keyof typeof colorType;
  className?: string;
}

const Txt: FC<PropsWithChildren<TxtProps>> = ({
  color = "black",
  typography = "p",
  children,
  className,
}) => (
  <span
    className={classNames(
      className,
      colorType[color],
      typographyType[typography]
    )}
  >
    {children}
  </span>
);

export default Txt;